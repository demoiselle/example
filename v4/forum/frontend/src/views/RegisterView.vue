<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const auth = useAuthStore()
const router = useRouter()
const firstName = ref('')
const email = ref('')
const pass = ref('')
const error = ref('')
const success = ref('')

async function register() {
  error.value = ''
  success.value = ''
  try {
    await auth.register(firstName.value, email.value, pass.value)
    success.value = 'Cadastro realizado! Faça login.'
    setTimeout(() => router.push('/login'), 1500)
  } catch (e: any) {
    error.value = e.response?.data?.[0]?.error || 'Erro ao cadastrar'
  }
}
</script>

<template>
  <div class="card" style="max-width: 400px; margin: 4rem auto;">
    <h2 style="margin-bottom: 1rem;">Cadastrar</h2>
    <form @submit.prevent="register">
      <div class="form-group">
        <label>Nome</label>
        <input v-model="firstName" required minlength="3" style="width:100%" />
      </div>
      <div class="form-group">
        <label>Email</label>
        <input v-model="email" type="email" required style="width:100%" />
      </div>
      <div class="form-group">
        <label>Senha</label>
        <input v-model="pass" type="password" required minlength="8" style="width:100%" />
      </div>
      <p class="msg-error" v-if="error">{{ error }}</p>
      <p class="msg-success" v-if="success">{{ success }}</p>
      <div style="display:flex; gap:.5rem; margin-top:1rem;">
        <button type="submit" class="btn-success">Cadastrar</button>
        <router-link to="/login" style="padding:.4rem .8rem;">Voltar</router-link>
      </div>
    </form>
  </div>
</template>
