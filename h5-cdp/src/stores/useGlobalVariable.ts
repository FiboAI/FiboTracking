import { defineStore } from "pinia"

const GlobalVariable = defineStore('GlobalVariable', {
    state: () => ({
        event_list: []
    })
})