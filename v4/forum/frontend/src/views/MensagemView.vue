<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import api from '@/services/api'
import { useAuthStore } from '@/stores/auth'

interface Mensagem { id?: number; texto: string; topico?: any; user?: any }

const route = useRoute()
const auth = useAuthStore()
const topicoId = route.params.id as string
const mensagens = ref<Mensagem[]>([])
const texto = ref('')
const error = ref('')

async function loadMensagens() {
  try {
    const { data } = await api.get('/mensagem')
    const all: Mensagem[] = data.content || data || []
    mensagens.value = all.filter((m: any) => String(m.topico?.id) === topicoId)
  } catch { mensagens.value = [] }
}

async function save() {
  error.value = ''
  try {
    await api.post('/mensagem', {
      texto: texto.value,
      topico: { id: Number(topicoId) },
      user: { id: auth.user?.identity }
    })
    texto.value = ''
    await loadMensagens()
  } catch (e: any) {
    error.value = e.response?.data?.[0]?.error_description || 'Erro ao enviar'
  }
}

async function remove(msg: Mensagem) {
  if (!confirm('Remover mensagem?')) return
  await api.delete(`/mensagem/${msg.id}`)
  await loadMensagens()
}

onMounted(loadMensagens)
</script>

<template>
  <h2>Mensagens do Tópico #{{ topicoId }}</h2>
  <div class="card">
    <form @submit.prevent="save">
      <div class="form-group">
        <label>Mensagem</label>
        <textarea v-model="texto" required rows="3" style="width:100%"></textarea>
      </div>
      <p class="msg-error" v-if="error">{{ error }}</p>
      <button type="submit" class="btn-primary">Enviar</button>
    </form>
  </div>

  <table v-if="mensagens.length">
    <thead><tr><th>Usuário</th><th>Mensagem</th><th>Ações</th></tr></thead>
    <tbody>
      <tr v-for="m in mensagens" :key="m.id">
        <td>{{ m.user?.firstName || m.user?.email || '—' }}</td>
        <td>{{ m.texto }}</td>
        <td>
          <button class="btn-danger" @click="remove(m)">Remover</button>
        </td>
      </tr>
    </tbody>
  </table>
  <p v-else style="margin-top:1rem; color:#888;">Nenhuma mensagem encontrada.</p>

  <div style="margin-top:1rem;">
    <router-link to="/topicos">&larr; Voltar aos Tópicos</router-link>
  </div>
</template>
