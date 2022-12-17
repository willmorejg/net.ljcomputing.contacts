<template>
  <div class="container">
    <CenterLoadingSpinner :showCenterLoadingSpinner="store.getters.loading"></CenterLoadingSpinner>
    <ContactDetail></ContactDetail>
    <ContactsList></ContactsList>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import ContactDetail from '@/components/model/contact/ContactDetail.vue';
import ContactsList from '@/components/model/contact/ContactsList.vue';
import CenterLoadingSpinner from '@/components/common/CenterLoadingSpinner.vue';
import store from '@/store';

export default defineComponent({
  name: 'ContactsView',
  components: {
    ContactDetail,
    ContactsList,
    CenterLoadingSpinner,
  },
  data() {
      return {
        store
      }
  },
});
</script>