<template>
    <ul class="pagination justify-content-center">
        <li v-bind:class="disablePrevious()">
            <a class="page-link" href="javascript:void(0);" @click="previous()">Previous</a>
        </li>
        <li class="page-item" v-for="p in pages()">
            <a v-bind:class="active(p)" href="javascript:void(0);" @click="setActive(p)">{{ p }}</a>
        </li>
        <li v-bind:class="disableNext()">
            <a class="page-link" href="javascript:void(0);" @click="next()">Next</a>
        </li>
    </ul>
    <div class="text-center">
        Page {{ page }} of {{ pages() }} (Total records: {{ totalElements }})
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import store from '@/store';

export default defineComponent({
    name: 'Pagnation',
    props: {
        itemsPerPage: {
            type: Number,
            required: true,
        },
        boundData: {
            type: Array,
            required: true,
        },
    },
    data() {
        return {
            page: 1,
            totalElements: 0
        }
    },
    methods: {
        previous: function () {
            this.page--
            store.dispatch('reload')
        },
        disablePrevious: function () {
            let c = "page-item"

            if (this.page == 1) {
                c = c + " disabled"
            }

            return c
        },
        next: function () {
            this.page++
            store.dispatch('reload')
        },
        disableNext: function () {
            let c = "page-item"

            if (this.page == 0 || this.page == this.pages()) {
                c = c + " disabled"
            }

            return c
        },
        getPage: function () {
            return this.page
        },
        getRequestedPage: function () {
            return this.page - 1
        },
        active: function (page: number) {
            let c = "page-link"

            if (page === this.page) {
                c = c + " active"
            }

            return c
        },
        setTotalElements: function (totalElements: number) {
            this.totalElements = totalElements || 0

            if (this.page > this.pages()) {
                this.setActive(this.pages())
            }
        },
        getTotalElements: function () {
            return this.totalElements
        },
        pages: function () {
            let l = this.totalElements || 0
            let i = this.itemsPerPage
            let value = 1

            if(l != 0) {
                value = Math.ceil(l / i)
                if (value == 0) {
                    value = 1
                }
            }

            return value
        },
        setActive: function (page: number) {
            this.page = page || 1
            store.dispatch('reload')
        },
    },
})
</script>