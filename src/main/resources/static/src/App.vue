<template>
  <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-primary fs-6">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">Contacts App</a>
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link to="/view/home" class="nav-link">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link id="viewContacts" to="/view/contacts" class="nav-link">Contacts</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/view/about" class="nav-link">About</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="container fluid">
    <div class="card border-secondary mb-3">
      <h5 class="card-header">{{ $route.name }}</h5>
      <div class="card-body">
        <router-view />
      </div>
    </div>
  </div>
</template>

<style>
.router-link-active {
  color: rgb(33, 64, 95) !important;
  background-color: white;
  border-top-left-radius: 40px;
  border-bottom-left-radius: 40px;
  border-top-right-radius: 40px;
  border-bottom-right-radius: 40px;
}

.navbar-dark .navbar-nav .nav-link {
  color: white;
}

.nav-item {
  border: 1px black solid;
  border-top-left-radius: 40px;
  border-bottom-left-radius: 40px;
  border-top-right-radius: 40px;
  border-bottom-right-radius: 40px;
}


.nav-item> :hover {
  border: 1px rgb(18, 33, 102) solid;
  border-top-left-radius: 40px;
  border-bottom-left-radius: 40px;
  border-top-right-radius: 40px;
  border-bottom-right-radius: 40px;
  background-color: rgb(76, 91, 226);
}

.card {
  border-top-left-radius: 40px;
  border-bottom-left-radius: 40px;
  border-top-right-radius: 40px;
  border-bottom-right-radius: 40px;
}
</style>
