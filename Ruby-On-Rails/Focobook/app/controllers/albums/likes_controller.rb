class Albums::LikesController < LikesController
  before_action :authenticate_user!
  before_action :set_album

  def create
    @album = Album.find(params[:album_id])
    @like = @album.likes.new
    @like.user = current_user
    @like.save
    respond_to do |format|
      format.html { redirect_to @like }
      format.js { render :file => "likes/create_album.js.erb" }
    end
  end

  def destroy
    @like = Like.find(params[:id])
    @like.destroy
    respond_to do |format|
      format.html { redirect_to @like }
      format.js { render :file => "/likes/destroy_album.js.erb" }
    end
  end

  private
  def set_album
    @album = Album.find(params[:album_id]) if params[:id]
  end
end