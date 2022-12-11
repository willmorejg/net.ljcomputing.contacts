<template>
    <div class="container">
        <p>Contacts view</p>
        <div>
            <ContactDetail></ContactDetail>
            <ContactsList></ContactsList>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import ContactDetail from '@/components/ContactDetail.vue';
import ContactsList from '@/components/ContactsList.vue';
import store from '@/store';

export default defineComponent({
  name: 'ContactsView',
  components: {
    ContactDetail,
    ContactsList,
  },
  computed: {
    reload(): boolean {
      return store.state.reloadContactsList
    }
  },
});
</script>