<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '@/services/api'

interface Categoria { id?: number; description: string }
interface Topico { id?: number; titulo: string; descricao: string; categoria?: Categoria }

const topicos = ref<Topico[]>([])
const categorias = ref<Categoria[]>([])
const form = ref<Topico>({ titulo: '', descricao: '' })
const categoriaId = ref<number | ''>('')
const error = ref('')

async function loadTopicos() {
  try {
    const { data } = await api.get('/topico')
    topicos.value = data.content || data || []
  } catch { topicos.value = [] }
}

async function loadCategorias() {
  try {
    const { data } = await api.get('/categoria')
    categorias.value = data.content || data || []
  } catch { categorias.value = [] }
}

async function save() {
  error.value = ''
  try {
    const payload = { titulo: form.value.titulo, descricao: form.value.descricao, categoria: { id: categoriaId.value } }
    await api.post('/topico', payload)
    reset()
    await loadTopicos()
  } catch (e: any) {
    error.value = e.response?.data?.[0]?.error_description || 'Erro ao salvar'
  }
}

async function remove(topico: Topico) {
  if (!confirm(`Remover "${topico.titulo}"?`)) return
  await api.delete(`/topico/${topico.id}`)
  await loadTopicos()
}

function reset() {
  form.value = { titulo: '', descricao: '' }
  categoriaId.value = ''
}

onMounted(() => { loadTopicos(); loadCategorias() })
</script>

<template>
  <h2>Tópicos</h2>
  <div class="card">
    <form @submit.prevent="save">
      <div class="form-group">
        <label>Título</label>
        <input v-model="form.titulo" required minlength="1" style="width:100%" />
      </div>
      <div class="form-group">
        <label>Descrição</label>
        <input v-model="form.descricao" required minlength="1" style="width:100%" />
      </div>
      <div class="form-group">
        <label>Categoria</label>
        <select v-model="categoriaId" required style="width:100%">
          <option value="" disabled>Selecione...</option>
          <option v-for="c in categorias" :key="c.id" :value="c.id">{{ c.description }}</option>
        </select>
      </div>
      <p class="msg-error" v-if="error">{{ error }}</p>
      <div style="display:flex; gap:.5rem;">
        <button type="submit" class="btn-primary">Criar Tópico</button>
      </div>
    </form>
  </div>

  <table v-if="topicos.length">
    <thead><tr><th>Título</th><th>Descrição</th><th>Categoria</th><th>Ações</th></tr></thead>
    <tbody>
      <tr v-for="t in topicos" :key="t.id">
        <td>
          <router-link :to="`/topicos/${t.id}/mensagens`">{{ t.titulo }}</router-link>
        </td>
        <td>{{ t.descricao }}</td>
        <td>{{ t.categoria?.description }}</td>
        <td style="display:flex; gap:.3rem;">
          <router-link :to="`/topicos/${t.id}/mensagens`" class="btn-success" style="text-decoration:none; padding:.4rem .8rem;">Mensagens</router-link>
          <button class="btn-danger" @click="remove(t)">Remover</button>
        </td>
      </tr>
    </tbody>
  </table>
  <p v-else style="margin-top:1rem; color:#888;">Nenhum tópico encontrado.</p>
</template>
