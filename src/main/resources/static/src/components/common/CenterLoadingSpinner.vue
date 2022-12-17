<template>
    <div class="d-flex justify-content-center centered display-1" v-if="showCenterLoadingSpinner">
        <strong>Loading...</strong>
        <div class="spinner-border ms-auto" role="status" aria-hidden="true"></div>
    </div>
</template>

<style>
.centered {
    position: fixed;
    top: 50%;
    left: 50%;
    z-index: 1000;
    opacity: 0.5;
    filter: alpha(opacity=50);
    transform: translate(-50%, -50%);
    transform: -webkit-translate(-50%, -50%);
    transform: -moz-translate(-50%, -50%);
    transform: -ms-translate(-50%, -50%);
    color: rgb(0, 0, 0);
}
</style>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
    name: 'CenterLoadingSpinner',
    props: {
        showCenterLoadingSpinner: {
            type: Boolean,
            required: true
        }
    },
})
</script>