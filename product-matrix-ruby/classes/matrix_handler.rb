require './classes/square_matrix.rb'
require './classes/standard_algorithm.rb'
require './classes/strassen_algorithm.rb'
require 'benchmark'

# Dumb class used to handle algorithm
class MatrixHandler
  # Init vars
  @standard_algorithm = @strassen_algorithm = nil
  @a_matrix           = @b_matrix = nil

  # MatrixHandler initializer
  #
  # @param [int] Size The size of the SquareMatrix
  def initialize(size)
    # Uncomment these line in order to generate random matrixes
    @a_matrix = SquareMatrix.new(size).randomize
    @b_matrix = SquareMatrix.new(size).randomize

    # Uncomment these lines and fill the matrixes with values
    # Beware of the size parameter, which is set in "main.rb"
    # @a_matrix = SquareMatrix.new size
    # @b_matrix = SquareMatrix.new size
    # @a_matrix.data = Matrix.rows [
    #                                  [1, 2, 0, 1],
    #                                  [3, 4, -1, 1],
    #                                  [1, 0, 1, 2],
    #                                  [0, 1, 3, 4]
    #                              ]
    #
    # @b_matrix.data = Matrix.rows [
    #                                  [1, -1, 0, 1],
    #                                  [2, 0, 1, 1],
    #                                  [0, 1, 1, 0],
    #                                  [1, 0, 0, -1]
    #                              ]

    # Display our matrixes
    puts 'Matrix A:'
    @a_matrix.print

    puts 'Matrix B:'
    @b_matrix.print
  end

  # Perform
  #
  # Execute both algorithms on the matrixes and output results
  def perform
    # Execute the naive algorithm and puts the result
    @standard_algorithm = StandardAlgorithm.new @a_matrix, @b_matrix
    puts 'Naive Algorithm:'
    @standard_algorithm.perform.print

    # This use the Ruby library to check the result
    puts @standard_algorithm.result == @a_matrix * @b_matrix ? 'OK' : 'NOK'

    # Same as above, using Strassen algorithm
    @strassen_algorithm = StrassenAlgorithm.new @a_matrix, @b_matrix
    puts 'Strassen Algorithm:'
    @strassen_algorithm.perform.print
    puts @strassen_algorithm.result == @a_matrix * @b_matrix ? 'OK' : 'NOK'
  end

  # Benchmark
  #
  # @param [int] Size
  def self.benchmark(size)
    # A beautiful progressbar
    bar = ProgressBar.new size, :bar, :percentage, :elapsed

    # Output Benchmark in a file for further uses
    File.delete('benchmark.log') if File.exist?('benchmark.log')
    $stdout      = File.new('benchmark.log', 'w')
    $stdout.sync = true

    # Start the benchmark
    Benchmark.bm(15) do |row|
      # Sizes are 2^i
      Array.new(size) { |i| 2**i }.each do |i|
        # Init matrix
        @a_matrix = SquareMatrix.new i
        @b_matrix = SquareMatrix.new i

        # Randomize values
        @a_matrix.randomize
        @b_matrix.randomize

        # Execute and report both algorithms
        row.report("Naive-#{i}") { StandardAlgorithm.new(@a_matrix, @b_matrix).perform }
        row.report("Strassen-#{i}") { StrassenAlgorithm.new(@a_matrix, @b_matrix).perform }
        bar.increment!
      end
    end
    $stdout.sync = false
  end
end
