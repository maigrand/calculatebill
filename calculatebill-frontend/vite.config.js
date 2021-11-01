const {defineConfig} = require("vite");
const path = require("path");
const react = require("@vitejs/plugin-react");

export default defineConfig({
  plugins: [
    react()
  ],

  resolve: {
    alias: {
      '@/': path.resolve(__dirname, './src'),
    },
  },

  server: {
    port: 3000,
    strictPort: true,
    cors: true,
  }
})