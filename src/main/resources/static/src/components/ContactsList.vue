<template>
  <ErrorModal ref="errorModal"></ErrorModal>

  <div class="container">
    <div class="card">
      <h5 class="card-title">Contacts List</h5>
      <div class="card-body" v-if="contacts.length > 0">
        <div class="table-responsive">
          <table class="table table-striped table-hover table-bordered align-middle">
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
          <Pagnation ref="contactsPagnation" :bound-data="contacts" :items-per-page="itemsPerPage"></Pagnation>
        </div>
      </div>
      <div class="card-body" v-if="contacts.length == 0">
        No records.
      </div>
    </div>
  </div>

</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { mapState } from 'vuex';
import axios from '@/axios-instance'

import Contact from '@/model/Contact'
import ErrorModal from './ErrorModal.vue';
import Pagnation from './Pagnation.vue';
import store from '@/store';

let contacts: Contact[] = [{}];
let itemsPerPage: number = 2;

export default defineComponent({
  name: 'ContactsList',
  components: {
    ErrorModal,
    Pagnation,
  },
  mounted() {
    this.retrieve();
    store.dispatch('reloaded')
  },
  data() {
    return {
      itemsPerPage,
      contacts,
    };
  },
  methods: {
    retrieve: function () {
      let contactsPagnation = this.$refs.contactsPagnation as any
      let page: number = contactsPagnation ? contactsPagnation.getRequestedPage() : 0

      const setContacts = this.setContacts
      const setTotalElements = this.setTotalElements
      const showModal = this.showModal

      console.log('page', page, 'size', this.itemsPerPage)

      axios.get('api/contacts', { params: { 'page': page, 'pageSize': this.itemsPerPage } })
        .then(function (response) {
          console.log('response', response)
          store.dispatch('reloaded')
          setContacts(response.data.content);
          setTotalElements(response.data.totalElements)
        })
        .catch(function (error) {
          console.log('error', error);
          showModal(error.message)
        });
    },
    deleteContact: function (id: string) {
      const showModal = this.showModal
      const setTotalElements = this.setTotalElements
      let contactsPagnation = this.$refs.contactsPagnation as any

      axios.delete('api/contacts/' + id)
        .then(function (response) {
          setTotalElements(contactsPagnation.getTotalElements() - 1)
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
    setTotalElements: function (totalElements: number) {
      let contactsPagnation = this.$refs.contactsPagnation as any

      if (contactsPagnation) {
        contactsPagnation.setTotalElements(totalElements)
      }
    }
  },
  computed: mapState(['reloadContactsList']),
  watch: {
    reloadContactsList: function (newValue) {
      if (newValue) {
        this.retrieve()
      }
    },
  }
});
</script>
