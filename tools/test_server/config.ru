require 'rack/ssl-enforcer'

require_relative 'lib/test_server'

use Rack::SslEnforcer, :only => %r{/secure/}, :https_port => 38443

run TestServer
