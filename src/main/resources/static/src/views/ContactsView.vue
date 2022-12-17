<template>
    <div class="container">
        <div>
            <ContactDetail></ContactDetail>
            <ContactsList></ContactsList>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import ContactDetail from '@/components/model/contact/ContactDetail.vue';
import ContactsList from '@/components/model/contact/ContactsList.vue';
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