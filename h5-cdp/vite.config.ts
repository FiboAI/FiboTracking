import { defineConfig, loadEnv } from 'vite';
import vue from '@vitejs/plugin-vue';
import vueJsx from '@vitejs/plugin-vue-jsx';
import Components from 'unplugin-vue-components/vite';
import AutoImport from 'unplugin-auto-import/vite'
import { ArcoResolver } from 'unplugin-vue-components/resolvers';
import { resolve } from "path"

// https://vitejs.dev/config/
export default ({ mode }) => {
  return defineConfig({
    base: "./",
    server: {
      port: 9002,
      proxy: {
        "^/cdp-project/": {
          target: loadEnv(mode, process.cwd()).VITE_AXIOS_PROXY_URL,
          changeOrigin: true,
          secure: false,
          rewrite: (path) => path.replace(/^\/cdp-project/, '/cdp-project/'),
        }
      },
      host: '0.0.0.0'
    },
    plugins: [
      vue(),
      vueJsx({
        transformOn: true
      }),
      AutoImport({
        imports: [
          'vue',
          'vue-router',
        ],
        dts: "src/auto-imports.d.ts"
      }),
      Components({
        dts: true,
        resolvers: [ArcoResolver({
          importStyle: "less",
          resolveIcons: true
        })],
        // exclude: [/[\\/]src[\\/]components[\\/]/],
      })
    ],
    css: {
      preprocessorOptions: {
        less: {
          modifyVars: {
            'color-primary-6': "rgb(238,90,70)",
            'color-primary-5': "rgb(246,125,114)",
            'color-primary-7': "rgb(246,125,114)",
            'btn-primary-color-bg_disabled':'#efcfcb',
          },
          additionalData: `@import "${resolve(__dirname, 'src/assets/styles/base.less')}";`,
          javascriptEnabled: true,
        }
      }
    },
    resolve: {
      alias: {
        "@": resolve(__dirname, '.', 'src/'),
        "_styles": resolve(__dirname, '.', 'src/assets/styles/'),
        "_apis": resolve(__dirname, '.', 'src/apis/'),
        "_utils": resolve(__dirname, '.', 'src/utils/'),
        "_types": resolve(__dirname, '.', 'src/types/'),
        "_hooks": resolve(__dirname, '.', 'src/hooks/'),
        "_stores": resolve(__dirname, '.', 'src/stores/'),
        "_workers": resolve(__dirname, '.', 'src/workers/'),
        "_components": resolve(__dirname, '.', 'src/components/'),
      }
    }
  })
}
