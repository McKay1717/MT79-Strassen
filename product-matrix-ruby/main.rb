require 'byebug'
require 'progress_bar'
require 'ruby-prof'
require './classes/matrix_handler.rb'

# Uncomment these lines to see algorithm outputs
# The MatrixHandler params is the size, if you want to input your own data, check matrix_handler.rb
# matrix_handler = MatrixHandler.new 4
# matrix_handler.perform

# Uncomment this line to run a benchmark. Output will be located in the file "benchmark.log"
MatrixHandler.benchmark(14) # Warning size is in fact 2^size

# # Profiler, used to optimize
# result = RubyProf.profile do
#   matrix_handler = MatrixHandler.new 4
#   matrix_handler.perform
# end
#
# # Output into profiler.html
# printer = RubyProf::GraphHtmlPrinter.new result
# printer.print(File.new('profiler.html', 'w'))

exit
