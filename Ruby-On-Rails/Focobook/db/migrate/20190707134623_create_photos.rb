class CreatePhotos < ActiveRecord::Migration[5.2]
  def change
    create_table :photos do |t|
      t.string :title
      t.text :description
      t.boolean :sharing_mode, default: true
      t.references :user, index: true
      t.timestamps
    end

    add_foreign_key :photos, :users
  end
end
