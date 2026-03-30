<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '@/services/api'
import { useAuthStore } from '@/stores/auth'

interface Todo { id?: string; description: string; status: string; dateEnd: string; user?: any }

const auth = useAuthStore()
const todos = ref<Todo[]>([])
const form = ref<Todo>({ description: '', status: '', dateEnd: '' })
const editing = ref(false)
const statusOptions = ref<{ label: string; value: string }[]>([])
const error = ref('')

async function loadTodos() {
  try {
    const identity = auth.user?.identity
    const { data } = await api.get(`/user/${identity}`)
    todos.value = data.todos || []
  } catch { todos.value = [] }
}

async function loadStatus() {
  try {
    const { data } = await api.get('/constants/status')
    statusOptions.value = Object.entries(data).map(([k, v]) => ({ label: k, value: v as string }))
    if (statusOptions.value.length && !form.value.status) form.value.status = statusOptions.value[0].label
  } catch {}
}

async function save() {
  error.value = ''
  try {
    const payload = { ...form.value, user: { id: auth.user?.identity } }
    if (editing.value) {
      await api.put('/todo', payload)
    } else {
      await api.post('/todo', payload)
    }
    reset()
    await loadTodos()
  } catch (e: any) {
    error.value = e.response?.data?.[0]?.error_description || 'Erro ao salvar'
  }
}

async function remove(todo: Todo) {
  if (!confirm(`Remover "${todo.description}"?`)) return
  await api.delete(`/todo/${todo.id}`)
  await loadTodos()
}

function edit(todo: Todo) {
  form.value = { ...todo }
  editing.value = true
}

function reset() {
  form.value = { description: '', status: statusOptions.value[0]?.label || '', dateEnd: '' }
  editing.value = false
}

function formatDate(d: string) {
  if (!d) return ''
  return new Date(d).toLocaleDateString('pt-BR')
}

onMounted(() => { loadTodos(); loadStatus() })
</script>

<template>
  <h2>To-Do's</h2>
  <div class="card">
    <form @submit.prevent="save">
      <div class="form-group">
        <label>Descrição</label>
        <input v-model="form.description" required minlength="1" maxlength="128" style="width:100%" />
      </div>
      <div style="display:flex; gap:1rem;">
        <div class="form-group" style="flex:1">
          <label>Data</label>
          <input v-model="form.dateEnd" type="date" style="width:100%" />
        </div>
        <div class="form-group" style="flex:1">
          <label>Status</label>
          <select v-model="form.status" style="width:100%">
            <option v-for="s in statusOptions" :key="s.label" :value="s.label">{{ s.value }}</option>
          </select>
        </div>
      </div>
      <p class="msg-error" v-if="error">{{ error }}</p>
      <div style="display:flex; gap:.5rem;">
        <button type="submit" class="btn-primary">{{ editing ? 'Salvar' : 'Criar To-Do' }}</button>
        <button v-if="editing" type="button" @click="reset">Cancelar</button>
      </div>
    </form>
  </div>

  <table v-if="todos.length">
    <thead><tr><th>Data</th><th>Descrição</th><th>Status</th><th>Ações</th></tr></thead>
    <tbody>
      <tr v-for="t in todos" :key="t.id">
        <td>{{ formatDate(t.dateEnd) }}</td>
        <td>{{ t.description }}</td>
        <td>{{ t.status }}</td>
        <td style="display:flex; gap:.3rem;">
          <button class="btn-primary" @click="edit(t)">Editar</button>
          <button class="btn-danger" @click="remove(t)">Remover</button>
        </td>
      </tr>
    </tbody>
  </table>
  <p v-else style="margin-top:1rem; color:#888;">Nenhum to-do encontrado.</p>
</template>
