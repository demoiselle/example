<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const auth = useAuthStore()
const router = useRouter()
const username = ref('admin@demoiselle.org')
const password = ref('123456')
const error = ref('')

async function login() {
  error.value = ''
  try {
    await auth.login(username.value, password.value)
    router.push('/livros')
  } catch (e: any) {
    error.value = e.response?.data?.[0]?.error || 'Erro ao fazer login'
  }
}
</script>

<template>
  <div class="card" style="max-width: 400px; margin: 4rem auto;">
    <h2 style="margin-bottom: 1rem;">Login</h2>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="email">Email</label>
        <input id="email" v-model="username" type="text" required style="width:100%" />
      </div>
      <div class="form-group">
        <label for="pass">Senha</label>
        <input id="pass" v-model="password" type="password" required style="width:100%" />
      </div>
      <p class="msg-error" v-if="error">{{ error }}</p>
      <div style="display:flex; gap:.5rem; margin-top:1rem;">
        <button type="submit" class="btn-success">Entrar</button>
        <router-link to="/register" style="padding:.4rem .8rem;">Cadastrar</router-link>
      </div>
      <p style="margin-top:1rem; font-size:.8rem; color:#888;">Email: admin@demoiselle.org / Senha: 123456</p>
    </form>
  </div>
</template>
