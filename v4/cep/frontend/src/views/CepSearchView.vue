<script setup lang="ts">
import { ref, onMounted, watch } from 'vue'
import api from '@/services/api'

interface Cep {
  logradouro: string
  bairroIni: string
  cidade: string
  uf: string
  cep: string
}

const searchType = ref<'cep' | 'uf' | 'cidade'>('cep')
const cepInput = ref('')
const selectedUf = ref('')
const selectedCidade = ref('')

const ufs = ref<string[]>([])
const cidades = ref<string[]>([])
const results = ref<Cep[]>([])
const error = ref('')
const loading = ref(false)

async function loadUfs() {
  try {
    const { data } = await api.get('/ufs')
    ufs.value = data
  } catch {}
}

async function loadCidades() {
  if (!selectedUf.value) { cidades.value = []; return }
  try {
    const { data } = await api.get(`/cidades/${selectedUf.value}`)
    cidades.value = data
  } catch { cidades.value = [] }
}

watch(selectedUf, () => {
  selectedCidade.value = ''
  cidades.value = []
  if (searchType.value !== 'cep') loadCidades()
})

async function search() {
  error.value = ''
  results.value = []
  loading.value = true
  try {
    let params: Record<string, string> = {}
    if (searchType.value === 'cep') {
      if (!cepInput.value.trim()) { error.value = 'Informe o CEP'; loading.value = false; return }
      params.cep = cepInput.value.trim()
    } else if (searchType.value === 'uf') {
      if (!selectedUf.value) { error.value = 'Selecione a UF'; loading.value = false; return }
      params.uf = selectedUf.value
    } else {
      if (!selectedCidade.value) { error.value = 'Selecione a cidade'; loading.value = false; return }
      params.cidade = selectedCidade.value
    }
    const { data } = await api.get('/ceps', { params })
    results.value = Array.isArray(data) ? data : [data]
  } catch (e: any) {
    error.value = e.response?.data?.message || 'Erro ao buscar CEP'
  } finally {
    loading.value = false
  }
}

onMounted(loadUfs)
</script>

<template>
  <h2>Buscar CEP</h2>
  <div class="card">
    <div class="form-group">
      <label>Tipo de busca</label>
      <select v-model="searchType" style="width:100%">
        <option value="cep">Por CEP</option>
        <option value="uf">Por UF</option>
        <option value="cidade">Por Cidade</option>
      </select>
    </div>

    <div v-if="searchType === 'cep'" class="form-group">
      <label>CEP</label>
      <input v-model="cepInput" placeholder="00000000" maxlength="9" style="width:100%" />
    </div>

    <div v-if="searchType === 'uf' || searchType === 'cidade'" class="form-group">
      <label>UF</label>
      <select v-model="selectedUf" style="width:100%">
        <option value="">Selecione...</option>
        <option v-for="u in ufs" :key="u" :value="u">{{ u }}</option>
      </select>
    </div>

    <div v-if="searchType === 'cidade'" class="form-group">
      <label>Cidade</label>
      <select v-model="selectedCidade" style="width:100%">
        <option value="">Selecione...</option>
        <option v-for="c in cidades" :key="c" :value="c">{{ c }}</option>
      </select>
    </div>

    <p class="msg-error" v-if="error">{{ error }}</p>
    <button class="btn-primary" @click="search" :disabled="loading">
      {{ loading ? 'Buscando...' : 'Buscar' }}
    </button>
  </div>

  <table v-if="results.length">
    <thead>
      <tr>
        <th>CEP</th>
        <th>Logradouro</th>
        <th>Bairro</th>
        <th>Cidade</th>
        <th>UF</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(r, i) in results" :key="i">
        <td>{{ r.cep }}</td>
        <td>{{ r.logradouro }}</td>
        <td>{{ r.bairroIni }}</td>
        <td>{{ r.cidade }}</td>
        <td>{{ r.uf }}</td>
      </tr>
    </tbody>
  </table>
  <p v-else-if="!loading && !error" style="margin-top:1rem; color:#888;">Nenhum resultado.</p>
</template>
