class AddImagesToAlbums < ActiveRecord::Migration[5.2]
  def change
    add_column :albums, :images, :json
  end
end
