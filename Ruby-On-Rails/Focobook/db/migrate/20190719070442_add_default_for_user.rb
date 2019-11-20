class AddDefaultForUser < ActiveRecord::Migration[5.2]
  def up
    change_column :users, :activated, :boolean, default: true
  end

  def down
    change_column :users, :activated, :boolean, default: nil
  end
end
