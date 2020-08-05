(ns install.mactools
 (:require [common.sh :refer [sh! expand-home]]))


(defn plugin-manager []
  ;(sh! "curl" "-fLo ~/.vim/autoload/plug.vim" "--create-dirs"
   ; "https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim")

  (sh! "mkdir" "-p" "~/.config/nvim")

  (spit
   (expand-home "~/.config/nvim/init.vim") 
   "
call plug#begin(stdpath('data') . '/plugged')

Plug 'liuchengxu/vim-better-default'
Plug 'easymotion/vim-easymotion'
Plug 'guns/vim-sexp'
Plug 'tpope/vim-sexp-mappings-for-regular-people'

Plug 'ncm2/float-preview.nvim'


Plug 'jiangmiao/auto-pairs', { 'tag': 'v2.0.0' }
Plug 'w0rp/ale'
Plug 'liuchengxu/vim-clap'


Plug 'Olical/conjure', { 'tag': 'v3.4.0' }

Plug 'tpope/vim-dispatch'
Plug 'clojure-vim/vim-jack-in'
Plug 'radenling/vim-dispatch-neovim'

call plug#end()


let g:float_preview#docked = 0
let g:float_preview#max_width = 80
let g:float_preview#max_height = 40

let g:ale_linters = {
      \\ 'clojure': ['clj-kondo', 'joker']
      \\}

let g:clap_provider_grep_delay = 50
let g:clap_provider_grep_opts = '-H --no-heading --vimgrep --smart-case --hidden -g \"!.git/\"'
nnoremap <leader>* :Clap grep ++query=<cword><cr>
nnoremap <leader>fg :Clap grep<cr>
nnoremap <leader>ff :Clap files --hidden<cr>
nnoremap <leader>fb :Clap buffers<cr>
nnoremap <leader>fw :Clap windows<cr>
nnoremap <leader>fr :Clap history<cr>
nnoremap <leader>fh :Clap command_history<cr>
nnoremap <leader>fj :Clap jumps<cr>
nnoremap <leader>fl :Clap blines<cr>
nnoremap <leader>fL :Clap lines<cr>
nnoremap <leader>ft :Clap filetypes<cr>
nnoremap <leader>fm :Clap marks<cr>

"))


(defn brew-install [lib]
 (sh! "brew" "install" lib))


(defn vim []
  (brew-install "neovim")
  (plugin-manager)
  (prn ">>>>>>>> Use nvim and run :PlugInstall"))

(defn -main [& args]
  (let [tools ["rlwrap"
               "curl"
               "ripgrep"]]
     (doseq [tool tools]
      (brew-install tool))
  
    (vim)))

