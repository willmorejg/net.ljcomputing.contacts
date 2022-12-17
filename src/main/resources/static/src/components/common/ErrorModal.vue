<template>
    <div class="modal" id="errorModal" ref="$errorModal">
        <div class="modal-dialog">

            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">An Error Occured</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">
                    {{ errorMsg }}
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { Modal } from 'bootstrap'

let errorMsg: string = ""

export default defineComponent({
    name: 'ErrorModal',
    data() {
        return {
            errorMsg
        };
    },
    mounted() {
    },
    methods: {
        showModal: function (message: string) {
            this.errorMsg = message
            const modal = this.$refs.$errorModal as any
            new Modal(modal).show()
        },
    },
});
</script>
