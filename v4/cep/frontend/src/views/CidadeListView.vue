<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import api from '@/services/api'

const route = useRoute()
const uf = route.params.uf as string
const cidades = ref<string[]>([])
const error = ref('')

async function loadCidades() {
  try {
    const { data } = await api.get(`/cidades/${uf}`)
    cidades.value = data
  } catch {
    error.value = 'Erro ao carregar cidades'
  }
}

onMounted(loadCidades)
</script>

<template>
  <h2>Cidades — {{ uf }}</h2>
  <p class="msg-error" v-if="error">{{ error }}</p>
  <table v-if="cidades.length">
    <thead><tr><th>Cidade</th></tr></thead>
    <tbody>
      <tr v-for="c in cidades" :key="c">
        <td>{{ c }}</td>
      </tr>
    </tbody>
  </table>
  <p v-else-if="!error" style="margin-top:1rem; color:#888;">Carregando...</p>
</template>
