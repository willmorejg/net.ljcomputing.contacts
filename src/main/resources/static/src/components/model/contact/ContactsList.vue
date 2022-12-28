<template>
  <ErrorModal ref="errorModal"></ErrorModal>

  <div class="container">
    <div class="card">
      <h5 class="card-title">Contacts List</h5>
      <div class="card-body" v-if="store.getters.contactsPageResponse.totalElements > 0">
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
              <tr v-for="contact in store.getters.contactsPageResponse.data">
                <td>{{ contact.id }}</td>
                <td>{{ contact.givenName }}</td>
                <td>{{ contact.middleName }}</td>
                <td>{{ contact.surname }}</td>
                <td>{{ contact.suffix }}</td>
                <td>
                  <a href="javascript:void(0);" v-bind:id="createId('edit', contact.id)" @click="editContact(contact.id)">Edit</a>&nbsp;
                  <a href="javascript:void(0);" v-bind:id="createId('delete', contact.id)" @click="deleteContact(contact.id)">Delete</a>
                </td>
              </tr>
            </tbody>
          </table>
          <Pagnation ref="contactsPagnation" :bound-data="store.getters.contactsPageResponse.data" :items-per-page="store.getters.contactsPageRequest.itemsPerPage"></Pagnation>
        </div>
      </div>
      <div id="contactsNoRecords" class="card-body" v-if="store.getters.contactsPageResponse.totalElements == 0">
        No records.
      </div>
     </div>
  </div>

</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { mapState } from 'vuex';

import Contact from '@/model/contact/Contact'
import ContactsServices from '@/service/ContactsService';
import ErrorModal from '../../common/ErrorModal.vue';
import Pagnation from '../../common/Pagnation.vue';
import CenterLoadingSpinner from '../../common/CenterLoadingSpinner.vue';
import store from '@/store';
import PageResponse from '@/model/shared/PageResponse';

let contacts: Contact[] = [{}];
let itemsPerPage: number = 2;

export default defineComponent({
  name: 'ContactsList',
  components: {
    ErrorModal,
    Pagnation,
    CenterLoadingSpinner,
  },
  mounted() {
    this.retrieve();
  },
  data() {
    return {
      itemsPerPage,
      contacts,
      store,
    };
  },
  methods: {
    retrieve: function () {
      const showModal = this.showModal
      const setTotalElements = this.setTotalElements
      let contactsPagnation = this.$refs.contactsPagnation as any
      let page: number = contactsPagnation ? contactsPagnation.getRequestedPage() : 0
      ContactsServices.setActivePage(page)
      ContactsServices.loadContacts().finally(() => {
        console.log('store.getters.contactsPageResponse', store.getters.contactsPageResponse)
        let response: PageResponse = store.getters.contactsPageResponse

        if(response.hasError()) {
          showModal(response.errorMessage as string)
        } else {
          setTotalElements(store.getters.contactsPageResponse.totalElements)
        }
      })
    },
    deleteContact: function (id: string) {
      ContactsServices.deleteContact(id)
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
    },
    createId: function(prefix: string, id: string) {
      return prefix + "-" + id
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
