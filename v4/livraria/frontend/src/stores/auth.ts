import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import api from '@/services/api'

export const useAuthStore = defineStore('auth', () => {
  const token = ref(localStorage.getItem('token') || '')

  const isAuthenticated = computed(() => !!token.value)

  function parseJwt(t: string) {
    try {
      return JSON.parse(atob(t.split('.')[1]))
    } catch { return null }
  }

  const user = computed(() => parseJwt(token.value))

  async function login(username: string, password: string) {
    const { data } = await api.post('/auth', { username, password })
    token.value = data
    localStorage.setItem('token', data)
  }

  async function register(firstName: string, email: string, pass: string) {
    await api.post('/user', { firstName, email, pass, perfil: 'USUARIO' })
  }

  function logout() {
    token.value = ''
    localStorage.removeItem('token')
  }

  return { token, isAuthenticated, user, login, register, logout }
})
