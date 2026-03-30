<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '@/services/api'

interface Categoria { id?: number; description: string }

const categorias = ref<Categoria[]>([])
const form = ref<Categoria>({ description: '' })
const editing = ref(false)
const error = ref('')

async function loadCategorias() {
  try {
    const { data } = await api.get('/categoria')
    categorias.value = data.content || data || []
  } catch { categorias.value = [] }
}

async function save() {
  error.value = ''
  try {
    if (editing.value) {
      await api.put('/categoria', form.value)
    } else {
      await api.post('/categoria', form.value)
    }
    reset()
    await loadCategorias()
  } catch (e: any) {
    error.value = e.response?.data?.[0]?.error_description || 'Erro ao salvar'
  }
}

async function remove(cat: Categoria) {
  if (!confirm(`Remover "${cat.description}"?`)) return
  await api.delete(`/categoria/${cat.id}`)
  await loadCategorias()
}

function edit(cat: Categoria) {
  form.value = { ...cat }
  editing.value = true
}

function reset() {
  form.value = { description: '' }
  editing.value = false
}

onMounted(loadCategorias)
</script>

<template>
  <h2>Categorias</h2>
  <div class="card">
    <form @submit.prevent="save">
      <div class="form-group">
        <label>Descrição</label>
        <input v-model="form.description" required minlength="1" style="width:100%" />
      </div>
      <p class="msg-error" v-if="error">{{ error }}</p>
      <div style="display:flex; gap:.5rem;">
        <button type="submit" class="btn-primary">{{ editing ? 'Salvar' : 'Criar Categoria' }}</button>
        <button v-if="editing" type="button" @click="reset">Cancelar</button>
      </div>
    </form>
  </div>

  <table v-if="categorias.length">
    <thead><tr><th>ID</th><th>Descrição</th><th>Ações</th></tr></thead>
    <tbody>
      <tr v-for="c in categorias" :key="c.id">
        <td>{{ c.id }}</td>
        <td>{{ c.description }}</td>
        <td style="display:flex; gap:.3rem;">
          <button class="btn-primary" @click="edit(c)">Editar</button>
          <button class="btn-danger" @click="remove(c)">Remover</button>
        </td>
      </tr>
    </tbody>
  </table>
  <p v-else style="margin-top:1rem; color:#888;">Nenhuma categoria encontrada.</p>
</template>
