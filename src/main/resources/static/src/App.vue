<template>
  <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-primary">
    <div class="container container-fluid">
      <a class="navbar-brand" href="/">Contacts App</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
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
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}

.navbar-dark .navbar-nav .nav-link {
  color: white;
}

.nav-item {
  border: 1px black solid;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}


.nav-item> :hover {
  border: 1px rgb(18, 33, 102) solid;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  background-color: rgb(76, 91, 226);
}

.card {
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}
</style>
