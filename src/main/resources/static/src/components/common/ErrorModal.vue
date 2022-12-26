<template>
    <div class="modal" id="errorModal" ref="$errorModal">
        <div class="modal-dialog">

            <div class="modal-content bg-light text-dark">

                <div class="modal-header">
                    <h5 class="modal-title">An Error Occured</h5>
                </div>

                    <div class="modal-body">
                        {{ errorMsg }}
                    </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="close">Close</button>
                </div>

            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { Modal } from 'bootstrap'

let modal = null as any
let errorMsg: string = ""

export default defineComponent({
    name: 'ErrorModal',
    data() {
        return {
            errorMsg
        };
    },
    mounted() {
        let m = this.$refs.$errorModal as any
        modal = new Modal(m)
    },
    methods: {
        showModal: function (message: string) {
            this.errorMsg = message
            modal.show()
        },
        close() {
            modal.hide()
        },
    },
});
</script>
