class StrassenAlgorithm
  @a_matrix = @b_matrix, @c_matrix = nil

  # StrassenAlgorithm initializer
  #
  # @param[SquareMatrix]
  # @param[SquareMatrix]
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
    @c_matrix = SquareMatrix.new @a_matrix.size

    # If there's only one element, return the product
    if @a_matrix.size == 1
      @c_matrix[0, 0] = @a_matrix[0, 0] * @b_matrix[0, 0]
      return @c_matrix
    end

    # Split matrixes in 4 equal parts
    a11, a12, a21, a22 = @a_matrix.split
    b11, b12, b21, b22 = @b_matrix.split

    # Execute recursivity as stated by Strassen Algorithm
    q1 = StrassenAlgorithm.new(a11 - a12, b22).perform
    q2 = StrassenAlgorithm.new(a21 - a22, b11).perform
    q3 = StrassenAlgorithm.new(a22, b11 + b21).perform
    q4 = StrassenAlgorithm.new(a11, b12 + b22).perform
    q5 = StrassenAlgorithm.new(a11 + a22, b22 - b11).perform
    q6 = StrassenAlgorithm.new(a11 + a21, b11 + b12).perform
    q7 = StrassenAlgorithm.new(a12 + a22, b21 + b22).perform

    # Use Strassen formulas
    c11 = q1 - q3 - q5 + q7
    c12 = q4 - q1
    c21 = q2 + q3
    c22 = q5 + q6 - q2 - q4

    # Build and return the result
    rows = []

    m_size = @a_matrix.size / 2
    m_size_odd = @a_matrix.size.odd? ? m_size + 1: m_size

    (0..(m_size_odd - 1)).each { |row| rows.push c11.row(row).to_a[0, m_size_odd] + c12.row(row).to_a[0, m_size] }
    (0..(m_size - 1)).each { |row| rows.push c21.row(row).to_a[0, m_size_odd] + c22.row(row).to_a[0, m_size] }

    @c_matrix.data = Matrix.rows rows

    @c_matrix
  end
end
