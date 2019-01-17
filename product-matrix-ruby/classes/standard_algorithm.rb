class StandardAlgorithm
  @a_matrix = @b_matrix, @c_matrix = nil

  # StandardAlgorithm initializer
  #
  # @params [SquareMatrix]
  # @params [SquareMatrix]
  def initialize(a_matrix, b_matrix)
    @a_matrix = a_matrix
    @b_matrix = b_matrix
  end

  # Return the result
  #
  # @return [SquareMatrix || nil]
  def result
    @c_matrix
  end

  # Perform
  #
  # @return [SquareMatrix]
  def perform
    # Use naive algorithm
    size = @a_matrix.size - 1
    @c_matrix = SquareMatrix.new @a_matrix.size

    # Iterate over rows
    (0..size).each do |row_i|
      # Get the row
      row = @a_matrix.row row_i
      # Iterate over columns
      (0..size).each do |col_i|
        # Get the column
        col = @b_matrix.column col_i
        # Iterate over values
        (0..size).each do |val_i|
          @c_matrix[row_i, col_i] = @c_matrix[row_i, col_i] + row[val_i] * col[val_i]
        end
      end
    end

    @c_matrix
  end
end
