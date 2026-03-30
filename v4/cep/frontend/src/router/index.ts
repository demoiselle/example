import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', component: () => import('@/views/CepSearchView.vue') },
    { path: '/ufs', component: () => import('@/views/UfListView.vue') },
    { path: '/cidades/:uf', component: () => import('@/views/CidadeListView.vue') },
  ]
})

export default router
