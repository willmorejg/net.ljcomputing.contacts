<template>
  <ErrorModal ref="errorModal"></ErrorModal>

  <div class="container">
    <table>
      <thead>
        <tr>
          <th>Id</th>
          <th>Given Name</th>
          <th>Middle Name</th>
          <th>Surname</th>
          <th>Suffix</th>
          <th>&nbsp;</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="contact in contacts">
          <td>{{ contact.id }}</td>
          <td>{{ contact.givenName }}</td>
          <td>{{ contact.middleName }}</td>
          <td>{{ contact.surname }}</td>
          <td>{{ contact.suffix }}</td>
          <td>
            <a href="#" @click="editContact(contact.id)">Edit</a>&nbsp;
            <a href="#" @click="deleteContact(contact.id)">Delete</a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { mapState } from 'vuex';
import axios from '@/axios-instance'

import Contact from '@/model/Contact'
import ErrorModal from './ErrorModal.vue';
import store from '@/store';

let contacts: Contact[] = [{}];

export default defineComponent({
  name: 'ContactsList',
  components: {
    ErrorModal,
  },
  mounted() {
    this.retrieve();
    store.dispatch('reloaded')
  },
  data() {
    return {
      contacts,
    };
  },
  methods: {
    retrieve: function () {
      const setContacts = this.setContacts
      const showModal = this.showModal

      axios.get('api/contacts')
        .then(function (response) {
          store.dispatch('reloaded')
          setContacts(response.data);
        })
        .catch(function (error) {
          console.log('error', error);
          showModal(error.message)
        });
    },
    deleteContact: function (id: string) {
      const showModal = this.showModal

      axios.delete('api/contacts/' + id)
        .then(function (response) {
          store.dispatch('reload')
        })
        .catch(function (error) {
          console.log('error', error);
          showModal(error.message)
        });
    },
    editContact: function (id: string) {
      store.dispatch('contactId', id)
      store.dispatch('retrieve')
    },
    showModal: function (message: string) {
      const modal = this.$refs.errorModal as any
      modal.showModal(message)
    },
    setContacts: function (data: any) {
      this.contacts = data;
    },
  },
  computed: mapState(['reloadContactsList']),
  watch: {
    reloadContactsList: function (newValue) {
      if (newValue) {
        this.retrieve()
      }
    }
  }
});
</script>
