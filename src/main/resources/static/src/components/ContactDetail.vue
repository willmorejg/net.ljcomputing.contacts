<template>
  <ErrorModal ref="errorModal"></ErrorModal>

  <div class="card">
    <h5 class="card-title">Contact Details</h5>
    <div class="card-body">
      <div class="row">
        <div class="col-12 w-100">
          <p></p>
          <br />
        </div>
      </div>
      <form>
        <div class="row">
          <div class="col">
            <label for="givenName">Given Name*:</label>
            <input id="givenName" ref="contactDetailGivenName" type="text" placeholder="Given Name" class="form-control"
              v-model="contact.givenName" tabindex="1" />
            <div style="color: red;" v-if="v$.contact.givenName.$error">Required</div>
          </div>
          <div class="col">
            <label for="middleName">Middle Name:</label>
            <input id="middleName" type="text" placeholder="Middle Name" class="form-control"
              v-model="contact.middleName" tabindex="2" />
          </div>
          <div class="col">
            <label for="surname">Surname*:</label>
            <input id="surname" type="text" placeholder="Surname" class="form-control" v-model="contact.surname"
              tabindex="3" />
            <div style="color: red;" v-if="v$.contact.givenName.$error">Required</div>
          </div>
          <div class="col">
            <label for="suffix">Suffix:</label>
            <input id="suffix" type="text" placeholder="Suffix" class="form-control" v-model="contact.suffix"
              tabindex="4" />
          </div>
        </div>
        <div class="row">
          <div class="col-6 w-50">* indicates required field</div>
          <div class="col-3 w-25"></div>
          <div class="col-3 w-25">
            <button class="btn btn-secondary" type="button" @click="reset" tabindex="6">Cancel</button>
            <button class="btn btn-primary" type="button" @click="submit" tabindex="5">OK</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>


<script lang="ts">
import { defineComponent, ref } from 'vue';
import { mapState } from 'vuex';
import { useVuelidate } from '@vuelidate/core'
import { required } from '@vuelidate/validators'
import axios from '@/axios-instance'

import Contact from '@/model/Contact'
import ErrorModal from './ErrorModal.vue';
import store from '@/store';

let errorMsg: string = ""
let contact: Contact = new Contact("", "", "", "", "");

export default defineComponent({
  name: 'ContactDetail',
  components: {
    ErrorModal,
  },
  setup() {
    return { v$: useVuelidate() }
  },
  mounted() {
    this.reset();
    this.setGivenNameFocused();
  },
  data() {
    return {
      errorMsg,
      contact,
    };
  },
  validations() {
    return {
      contact: {
        givenName: { required },
        surname: { required },
      }
    }
  },
  methods: {
    submit: function () {
      const focusedElement = this.setGivenNameFocused
      const setContact = this.setContact;
      const resetForm = this.reset;
      const showModal = this.showModal
      const v$ = this.v$
      this.v$.$touch();

      if (!this.v$.$invalid) {
        axios.post('api/contacts', this.contact)
          .then(function (response) {
            resetForm;
            store.dispatch('reload')
            setContact({ givenName: "", middleName: "", surname: "", suffix: "", id: "" });
            v$.$reset()
          })
          .catch(function (error) {
            console.log('error', error);
            showModal(error.message)
          });
      }

      focusedElement
    },
    showModal: function (message: string) {
      const modal = this.$refs.errorModal as any
      modal.showModal(message)
    },
    reset: function () {
      this.setContact({ givenName: "", middleName: "", surname: "", suffix: "", id: "" });
      this.v$.$reset();
      this.setGivenNameFocused()
    },
    setContact: function (data: any) {
      this.contact = new Contact(data.givenName, data.middleName, data.surname, data.suffix, data.id);
    },
    loadContact: function () {
      const focusedElement = this.setGivenNameFocused
      const id = store.getters.contactId;
      const setContact = this.setContact;

      axios.get('api/contacts/' + id)
        .then(function (response) {
          setContact(response.data);
          store.dispatch('retrieved')
        })
        .catch(function (error) {
          console.log('error', error);
        });

      focusedElement
    },
    setGivenNameFocused: function () {
      const formGivenName = this.$refs.contactDetailGivenName as any
      formGivenName.focus();
    }
  },
  computed: mapState(['reloadContactsList', 'retrieveContact', 'contactId']),
  watch: {
    retrieveContact: function (newValue) {
      if (newValue) {
        this.loadContact()
        this.setGivenNameFocused()
      }
    },
    reloadContactsList: function (newValue) {
      if (newValue) {
        this.setGivenNameFocused()
      }
    }
  }
});
</script>
