<script setup lang="ts">
</script>

<template>
  <nav>
    <router-link to="/">Buscar CEP</router-link>
    <router-link to="/ufs">UFs</router-link>
  </nav>
  <main>
    <router-view />
  </main>
</template>

<style>
* { box-sizing: border-box; margin: 0; padding: 0; }
body { font-family: system-ui, sans-serif; background: #f5f5f5; color: #333; }
nav { display: flex; gap: 1rem; padding: 1rem; background: #2c3e50; }
nav a { color: #fff; text-decoration: none; font-size: .9rem; }
nav a.router-link-active { font-weight: bold; border-bottom: 2px solid #3498db; }
main { max-width: 900px; margin: 2rem auto; padding: 0 1rem; }
input, select { padding: .4rem .6rem; border: 1px solid #ccc; border-radius: 4px; font-size: .9rem; }
button { padding: .4rem .8rem; border: none; border-radius: 4px; cursor: pointer; font-size: .9rem; }
.btn-primary { background: #3498db; color: #fff; }
.btn-danger { background: #e74c3c; color: #fff; }
.btn-success { background: #27ae60; color: #fff; }
table { width: 100%; border-collapse: collapse; margin-top: 1rem; background: #fff; }
th, td { padding: .6rem; text-align: left; border-bottom: 1px solid #eee; }
th { background: #ecf0f1; }
.form-group { margin-bottom: .8rem; }
.form-group label { display: block; margin-bottom: .3rem; font-weight: 600; font-size: .85rem; }
.card { background: #fff; padding: 1.5rem; border-radius: 8px; box-shadow: 0 1px 3px rgba(0,0,0,.1); margin-bottom: 1.5rem; }
.msg-error { color: #e74c3c; font-size: .85rem; margin-top: .5rem; }
.msg-success { color: #27ae60; font-size: .85rem; margin-top: .5rem; }
</style>
