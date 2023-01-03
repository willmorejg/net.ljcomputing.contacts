<template>
  <ErrorModal ref="errorModal"></ErrorModal>

  <div class="container">
    <div class="card">
      <h5 class="card-title">Contacts List</h5>
      <div class="container">
        <div class="row">
          <div class="col-4">&nbsp;</div>
          <div class="col-3"><span class="filter-description"><b><i>Filter (Given Name or Surname):</i></b></span><input
              id="filterValue" placeholder="Filter Records By" v-model="filterValue" /></div>
        </div>
        <div class="row">
          <div class="col-4">&nbsp;</div>
          <div class="col-8">
            <button id="removeFilterValue" class="btn btn-sm btn-secondary align-self-center" type="button"
              @click="filterValue = ''; changeFilter();">Remove</button>
            <button id="removeFilterValue" class="btn btn-sm btn-primary align-self-center" type="button"
              @click="changeFilter">Apply</button>
          </div>
        </div>
      </div>
      <div class="card-body" v-if="store.getters.contactsPageResponse.totalElements > 0">
        <div class="table-responsive">
          <table class="table table-striped table-hover table-bordered align-middle">
            <thead>
              <tr>
                <th @click="changeSort('id')">Id<font-awesome-icon v-bind:icon="sortIcon('id')" /></th>
                <th @click="changeSort('givenName')">Given Name<font-awesome-icon v-bind:icon="sortIcon('givenName')" />
                </th>
                <th @click="changeSort('middleName')">Middle Name<font-awesome-icon
                    v-bind:icon="sortIcon('middleName')" /></th>
                <th @click="changeSort('surname')">Surname<font-awesome-icon v-bind:icon="sortIcon('surname')" /></th>
                <th @click="changeSort('suffix')">Suffix<font-awesome-icon v-bind:icon="sortIcon('suffix')" /></th>
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
                  <a href="javascript:void(0);" v-bind:id="createId('edit', contact.id)"
                    @click="editContact(contact.id)">Edit</a>&nbsp;
                  <a href="javascript:void(0);" v-bind:id="createId('delete', contact.id)"
                    @click="deleteContact(contact.id)">Delete</a>
                </td>
              </tr>
            </tbody>
          </table>
          <Pagnation ref="contactsPagnation" :bound-data="store.getters.contactsPageResponse.data"
            :items-per-page="store.getters.contactsPageRequest.itemsPerPage"></Pagnation>
        </div>
      </div>
      <div id="contactsNoRecords" class="card-body" v-if="store.getters.contactsPageResponse.totalElements == 0">
        No records.
      </div>
    </div>
  </div>

</template>

<style scoped>
.filter-description {
  font-size: 75%;
}
</style>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { mapState } from 'vuex';

import ContactsServices from '@/service/ContactsService';
import ErrorModal from '../../common/ErrorModal.vue';
import Pagnation from '../../common/Pagnation.vue';
import CenterLoadingSpinner from '../../common/CenterLoadingSpinner.vue';
import store from '@/store';
import PageResponse from '@/model/shared/PageResponse';

enum Direction {
  ASC = "ASC",
  DESC = "DESC",
}

let direction: Direction = Direction.ASC;
let field: string = "surname";
let filterValue: string = "";

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
      store,
      field,
      direction,
      filterValue,
    };
  },
  methods: {
    retrieve: function () {
      const showModal = this.showModal
      const setTotalElements = this.setTotalElements
      let contactsPagnation = this.$refs.contactsPagnation as any
      let page: number = contactsPagnation ? contactsPagnation.getRequestedPage() : 0
      ContactsServices.setActivePage(page)
      ContactsServices.setField(this.field)
      ContactsServices.setDirection(this.direction.toString())
      ContactsServices.loadContacts().finally(() => {
        console.log('store.getters.contactsPageResponse', store.getters.contactsPageResponse)
        let response: PageResponse = store.getters.contactsPageResponse

        if (response.hasError()) {
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
    setTotalElements: function (totalElements: number) {
      let contactsPagnation = this.$refs.contactsPagnation as any

      if (contactsPagnation) {
        contactsPagnation.setTotalElements(totalElements)
      }
    },
    createId: function (prefix: string, id: string) {
      return prefix + "-" + id
    },
    sortIcon: function (value: string) {
      let retValue: string[] = []

      if (value == this.field) {
        retValue = this.direction == Direction.ASC ? ['fa-solid', 'fa-sort-up'] : ['fa-solid', 'fa-sort-down']
      }

      return retValue;
    },
    changeSort: function (value: string) {
      let justSort: boolean = value == this.field

      if (justSort) {
        this.direction = this.direction == Direction.ASC ? Direction.DESC : Direction.ASC
      } else {
        this.direction = Direction.ASC
        this.field = value;
      }

      this.retrieve()
    },
    changeFilter: function () {
      ContactsServices.setFilterValue(this.filterValue)
      this.retrieve()
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
