class Photo < ApplicationRecord
  belongs_to :user
  has_many :likes, as: :likeable, dependent: :destroy
  has_one_attached :image
  scope :published_photo, -> {
    where(sharing_mode: true)
  }
end
