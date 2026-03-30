<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '@/services/api'

interface User { id?: string; firstName: string; email: string; perfil?: string }

const users = ref<User[]>([])

async function loadUsers() {
  try {
    const { data } = await api.get('/user')
    users.value = data.content || data || []
  } catch { users.value = [] }
}

async function remove(user: User) {
  if (!confirm(`Remover "${user.firstName}"?`)) return
  await api.delete(`/user/${user.id}`)
  await loadUsers()
}

onMounted(loadUsers)
</script>

<template>
  <h2>Usuários</h2>
  <table v-if="users.length">
    <thead><tr><th>Nome</th><th>Email</th><th>Perfil</th><th>Ações</th></tr></thead>
    <tbody>
      <tr v-for="u in users" :key="u.id">
        <td>{{ u.firstName }}</td>
        <td>{{ u.email }}</td>
        <td>{{ u.perfil }}</td>
        <td>
          <button class="btn-danger" @click="remove(u)">Remover</button>
        </td>
      </tr>
    </tbody>
  </table>
  <p v-else style="margin-top:1rem; color:#888;">Nenhum usuário encontrado.</p>
</template>
