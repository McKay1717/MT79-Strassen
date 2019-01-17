require 'matrix'

# Monkey-patch Matrix native class, in order to support value setting
class Matrix
  def []=(i, j, x)
    @rows[i][j] = x
  end
end

# Ruby's Matrix wrapper
class SquareMatrix
  @data = nil
  @size = nil

  attr_reader :size
  attr_reader :data
  attr_writer :data

  # SquareMatrix initializer
  #
  # @param [int]
  #
  # Init a Matrix and fill it with zeros
  def initialize(size)
    @data = Matrix.zero size, size
    @size = size
  end

  # Randomize
  #
  # Randomize values (range 0 to 100)
  #
  # @return [SquareMatrix] self
  def randomize
    @data = Matrix.build(@size, @size) { |_, _| rand(100) }
    self
  end

  # Display the matrix
  def print
    length = @data.to_a.flatten.max.to_s.size + 4
    puts @data.to_a.map { |a| a.map { |i| i.to_s.rjust(length) }.join }
  end

  # Split a matrix in 4 same sized matrixes
  #
  # @return [[SquareMatrix, SquareMatrix, SquareMatrix, SquareMatrix]]
  def split
    m_size = size / 2
    m_size = size.odd? ? m_size + 1 : m_size
    result = []

    2.times.each do |row|
      2.times.each do |col|
        temp = SquareMatrix.new m_size

        rows = data.to_a.slice(row * m_size, (row + 1) * m_size).map do |i|
          i.slice(col * m_size, (col + 1) * m_size)
        end

        rows.each_with_index do |row_i, i|
          row_i.each_with_index do |col_i, j|
            temp[i, j] = col_i
          end
        end
        result.push temp
      end
    end
    result
  end

  # method_missing
  #
  # @param [Symbol]
  # @param [args]
  # @param [block]
  #
  # Propagate missing methods to Ruby's Matrix
  def method_missing(method, *args, &block)
    if @data.respond_to? method
      @data.send method, *args, &block
    else
      super
    end
  end

  # +
  #
  # @param [SquareMatrix]
  #
  # Add the values of this matrix to another one
  #
  # @return [SquareMatrix]
  def +(other)
    r      = SquareMatrix.new(size)
    r.data = data + other.data
    r
  end

  # -
  #
  # @param [SquareMatrix]
  #
  # Substract the values of this matrix to another one
  #
  # @return [SquareMatrix]
  def -(other)
    r      = SquareMatrix.new(size)
    r.data = data - other.data
    r
  end

  # *
  #
  # @param [SquareMatrix]
  #
  # Perform Ruby's native product
  # Used for tests
  #
  # @return [SquareMatrix]
  def *(other)
    r      = SquareMatrix.new(size)
    r.data = data * other.data
    r
  end

  # ==
  #
  # @param [SquareMatrix]
  #
  # Check values of both matrixes
  #
  # @return [boolean]
  def ==(other)
    data == other.data
  end
end
