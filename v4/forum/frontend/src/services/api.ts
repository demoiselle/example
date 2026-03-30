import axios from 'axios'
import { useAuthStore } from '@/stores/auth'

const api = axios.create({ baseURL: '/api/v1' })

api.interceptors.request.use((config) => {
  const auth = useAuthStore()
  if (auth.token) config.headers.Authorization = `Token ${auth.token}`
  return config
})

api.interceptors.response.use(
  (r) => r,
  (err) => {
    if (err.response?.status === 401) {
      const auth = useAuthStore()
      auth.logout()
      window.location.href = '/login'
    }
    return Promise.reject(err)
  }
)

export default api
