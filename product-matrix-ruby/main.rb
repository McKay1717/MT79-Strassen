require 'byebug'
require 'progress_bar'
require './classes/matrix_handler.rb'

# Uncomment these lines to see algorithm outputs
# The MatrixHandler params is the size, if you want to input your own data, check matrix_handler.rb
matrix_handler = MatrixHandler.new 4
matrix_handler.perform

# Uncomment this line to run a benchmark. Output will be located in the file "benchmark.log"
# MatrixHandler.benchmark(12) # Warning size is in fact 2^size

exit
