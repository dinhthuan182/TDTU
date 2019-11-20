Rails.application.routes.draw do
  root to: "home_page#discovers"

  get '/feeds', to: "home_page#feeds"
  get '/managers/photo', to: "admin#photo"
  get '/managers/album', to: "admin#album"
  get '/managers/user', to: "admin#user"

  devise_for :users, :controllers => {:registrations => "registrations"}

  resources :users do
    member do
      get :followings, :followers
    end
  end

  resources :albums do
    resources :likes, only: [:create, :destroy], module: :albums
    resources :images, :only => [:create, :destroy]
  end

  resources :photos do
    resources :likes, only: [:create, :destroy], module: :photos
  end

  resources :relationships,       only: [:create, :destroy]
end
