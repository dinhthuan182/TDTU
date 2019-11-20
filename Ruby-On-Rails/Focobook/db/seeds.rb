# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)

require 'faker'
# Users
User.create!(first_name:  " User",
             last_name: "Admin",
             email: "admin@admin.com",
             password: "adminuser",
             admin:     true,
             activated: true)

30.times do |n|
  first_name  = Faker::Name.first_name
  last_name  = Faker::Name.last_name
  email = "example#{n+1}@gmail.com"
  password = "password"
  User.create!(first_name:  first_name,
               last_name:  last_name,
               email: email,
               password: password,
               activated: true)
end

# Following relationships
users = User.all
user  = users.first
followings = users[6..23]
followers = users[5..26]
followings.each { |followed| user.follow(followed) }
followers.each { |follower| follower.follow(user) }
