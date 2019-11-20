class RenewLikes < ActiveRecord::Migration[5.2]
  def change
    drop_table :likes
    create_table :likes do |t|
      t.integer :user_id
      t.references :likeable, polymorphic: true, index: true
      t.timestamps
    end

    add_index :likes, :user_id
  end
end
