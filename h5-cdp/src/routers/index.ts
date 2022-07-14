import { Router, createRouter, createWebHashHistory } from "vue-router";

const CreateRouter: () => Router = () => createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: "/",
            name: "login",
            alias: "/login",
            component: () => import('@/views/login.vue')
        },
        {
            path: "/home",
            name: "home",
            // redirect:'/overview/1',
            component: () => import("@/views/home.vue"),
            children: [
                {
                    path: "/overview",
                    redirect:'/overview/1',
                },
                {
                    path: "/overview/home",
                    component: () => import("@/views/overview/home.vue"),
                },
                {
                    path: "/overview/:overviewId",
                    name: "overview",
                    component: () => import('@/views/overview/index.vue'),
                },
                {
                    path: "/analysis",
                    name: "Analysis",
                    component: () => import("@/views/analysis/index.vue"),
                    children: [
                        {
                            path: "/analysis/event",
                            name: "AnalysisEvent",
                            component: () => import("@/views/analysis/event.vue")
                        },
                        {
                            path: "/analysis/funnel",
                            name: "AnalysisFunnel",
                            component: () => import("@/views/analysis/funnel.vue"),
                        },
                        {
                            path: "/analysis/remain",
                            name: "AnalysisRemain",
                            component: () => import("@/views/analysis/remain.vue"),
                        },
                        {
                            path: "/analysis/customQueries",
                            name: "AnalysisCustomQueries",
                            component: () => import("@/views/analysis/customQueries.vue"),
                        },
                    ]
                },
                {
                    path: "/passback",
                    name: "Passback",
                    component: () => import("@/views/passback/index.vue"),
                    children: [
                        {
                            path: "/passback/management",
                            name: "PassbackManagement",
                            component: () => import("@/views/passback/management.vue")
                        },
                        {
                            path: "/passback/strategies",
                            name: "PassbackStrategies",
                            component: () => import("@/views/passback/strategies.vue"),
                        },
                        {
                            path: "/passback/records",
                            name: "PassbackRecords",
                            component: () => import("@/views/passback/records.vue"),
                        },
                        {
                            path: "/passback/configure",
                            name: "PassbackConfigure",
                            component: () => import("@/views/passback/configure.vue"),
                        },
                        {
                            path: "/passback/events",
                            name: "PassbackEvents",
                            component: () => import("@/views/passback/events.vue"),
                        },
                    ]
                },
                {
                    path: "/data",
                    name: "Data",
                    component: () => import("@/views/data/index.vue"),
                    children: [
                        {
                            path: "/data/eventManagement",
                            name: "EventManagement",
                            component: () => import("@/views/data/eventManagement.vue")
                        },
                        {
                            path: "/data/propertyManagement",
                            name: "PropertyManagement",
                            component: () => import("@/views/data/propertyManagement.vue"),
                        },
                    ]
                },
                {
                    path: "/admin",
                    name: "Admin",
                    component: () => import("@/views/admin/index.vue"),
                    children: [
                        {
                            path: "/admin/accountManagement",
                            name: "AccountManagement",
                            component: () => import("@/views/admin/accountManagement.vue")
                        },
                        {
                            path: "/admin/roleManagement",
                            name: "RoleManagement",
                            component: () => import("@/views/admin/roleManagement.vue"),
                        },
                    ]
                },
                
            ]
        }
    ]
})

const router: Router = CreateRouter();

export default router