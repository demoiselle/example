<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '@/services/api'
import { useRouter } from 'vue-router'

const router = useRouter()
const ufs = ref<string[]>([])
const error = ref('')

async function loadUfs() {
  try {
    const { data } = await api.get('/ufs')
    ufs.value = data
  } catch {
    error.value = 'Erro ao carregar UFs'
  }
}

function viewCidades(uf: string) {
  router.push(`/cidades/${uf}`)
}

onMounted(loadUfs)
</script>

<template>
  <h2>Unidades Federativas</h2>
  <p class="msg-error" v-if="error">{{ error }}</p>
  <table v-if="ufs.length">
    <thead><tr><th>UF</th><th>Ações</th></tr></thead>
    <tbody>
      <tr v-for="uf in ufs" :key="uf">
        <td>{{ uf }}</td>
        <td>
          <button class="btn-primary" @click="viewCidades(uf)">Ver Cidades</button>
        </td>
      </tr>
    </tbody>
  </table>
  <p v-else-if="!error" style="margin-top:1rem; color:#888;">Carregando...</p>
</template>
