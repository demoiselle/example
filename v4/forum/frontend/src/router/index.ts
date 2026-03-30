import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', redirect: '/login' },
    { path: '/login', component: () => import('@/views/LoginView.vue') },
    { path: '/register', component: () => import('@/views/RegisterView.vue') },
    { path: '/categorias', component: () => import('@/views/CategoriaView.vue'), meta: { auth: true } },
    { path: '/topicos', component: () => import('@/views/TopicoView.vue'), meta: { auth: true } },
    { path: '/topicos/:id/mensagens', component: () => import('@/views/MensagemView.vue'), meta: { auth: true } },
    { path: '/users', component: () => import('@/views/UserView.vue'), meta: { auth: true } },
  ]
})

router.beforeEach((to) => {
  const auth = useAuthStore()
  if (to.meta.auth && !auth.isAuthenticated) return '/login'
})

export default router
