<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '@/services/api'
import { useAuthStore } from '@/stores/auth'

interface Livro { id?: number; titulo: string; descricao: string; conteudo: string; url: string; usuario?: any }

const auth = useAuthStore()
const livros = ref<Livro[]>([])
const form = ref<Livro>({ titulo: '', descricao: '', conteudo: '', url: '' })
const editing = ref(false)
const error = ref('')

async function loadLivros() {
  try {
    const { data } = await api.get('/livro')
    livros.value = data.content || data || []
  } catch { livros.value = [] }
}

async function save() {
  error.value = ''
  try {
    const payload = { ...form.value, usuario: { id: auth.user?.identity } }
    if (editing.value) {
      await api.put('/livro', payload)
    } else {
      await api.post('/livro', payload)
    }
    reset()
    await loadLivros()
  } catch (e: any) {
    error.value = e.response?.data?.[0]?.error_description || 'Erro ao salvar'
  }
}

async function remove(livro: Livro) {
  if (!confirm(`Remover "${livro.titulo}"?`)) return
  await api.delete(`/livro/${livro.id}`)
  await loadLivros()
}

function edit(livro: Livro) {
  form.value = { ...livro }
  editing.value = true
}

function reset() {
  form.value = { titulo: '', descricao: '', conteudo: '', url: '' }
  editing.value = false
}

onMounted(loadLivros)
</script>

<template>
  <h2>Livros</h2>
  <div class="card">
    <form @submit.prevent="save">
      <div class="form-group">
        <label>Título</label>
        <input v-model="form.titulo" required minlength="1" style="width:100%" />
      </div>
      <div class="form-group">
        <label>Descrição</label>
        <input v-model="form.descricao" style="width:100%" />
      </div>
      <div class="form-group">
        <label>Conteúdo</label>
        <textarea v-model="form.conteudo" rows="3" style="width:100%"></textarea>
      </div>
      <div class="form-group">
        <label>URL</label>
        <input v-model="form.url" type="url" style="width:100%" />
      </div>
      <p class="msg-error" v-if="error">{{ error }}</p>
      <div style="display:flex; gap:.5rem;">
        <button type="submit" class="btn-primary">{{ editing ? 'Salvar' : 'Criar Livro' }}</button>
        <button v-if="editing" type="button" @click="reset">Cancelar</button>
      </div>
    </form>
  </div>

  <table v-if="livros.length">
    <thead><tr><th>Título</th><th>Descrição</th><th>URL</th><th>Ações</th></tr></thead>
    <tbody>
      <tr v-for="l in livros" :key="l.id">
        <td>{{ l.titulo }}</td>
        <td>{{ l.descricao }}</td>
        <td><a v-if="l.url" :href="l.url" target="_blank">Link</a></td>
        <td style="display:flex; gap:.3rem;">
          <button class="btn-primary" @click="edit(l)">Editar</button>
          <button class="btn-danger" @click="remove(l)">Remover</button>
        </td>
      </tr>
    </tbody>
  </table>
  <p v-else style="margin-top:1rem; color:#888;">Nenhum livro encontrado.</p>
</template>
