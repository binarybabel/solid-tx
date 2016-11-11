require 'grape'

class TestServer < Grape::API

  class CoreAPI < Grape::API

    format :json

    get :hello do
      'Hello World'
    end

    get     (:method){ 'GET' }
    put     (:method){ 'PUT' }
    post    (:method){ 'POST' }
    delete  (:method){ 'DELETE' }


    params do
      requires :value, type: String
    end
    post :data do
      params[:value]
    end

    namespace :auth do
      http_basic do |username, password|
        { 'TimBL' => 'Nov89' }[username] == password
      end

      get :secret do
        'Morocco Mole'
      end
    end  # /auth

    namespace :secure do
      get :hello do
        'Walt sent me'
      end

      namespace :auth do
        http_basic do |username, password|
          { 'TimBL' => 'Nov89' }[username] == password
        end

        get :secret do
          'Secret Squirrel'
        end

      end  # /auth
    end  # /secure

  end

  class PayloadAPI < Grape::API

    format :json

    post :endpoint do
      {
          attr: {
              time: 100
          },
          data: {
              person: {
                  1 => { :firstName => 'Shaggy',  :lastName => 'Rogers',
                         :hometown_id => '4f38f330-a659-11e4-bcd8-0800200c9a66',
                         :favorite_colors => %w{green},
                         :favorite_things_ids => %w{f2f16b93-1150-4799-83ec-bf75c85c1989 8b7d6ab0-a659-11e4-bcd8-0800200c9a66}
                  },
                  2 => { :firstName => 'Fred',    :lastName => 'Jones',
                         :hometown_id => '4f38f330-a659-11e4-bcd8-0800200c9a66',
                         :favorite_colors => %w{white blue orange},
                         :favorite_things_ids => %w(8639d2c5-94ff-4350-8239-358839a5c0fa 134a350a-aead-414f-81a1-29f83cc59b57)
                  },
                  3 => { :firstName => 'Daphne',  :lastName => 'Blake',
                         :hometown_id => '4f38f330-a659-11e4-bcd8-0800200c9a66',
                         :favorite_colors => %w{purple green}
                  },
                  4 => { :firstName => 'Velma',   :lastName => 'Dinkley',
                         :hometown_id => '4f38f330-a659-11e4-bcd8-0800200c9a66',
                         :favorite_colors => %w{orange}
                  },
              },
              place: {
                  '4f38f330-a659-11e4-bcd8-0800200c9a66' => { :name => 'Coolsville' }
              },
              thing: {
                  '8639d2c5-94ff-4350-8239-358839a5c0fa' => { :name => 'Mystery Machine' },
                  'f2f16b93-1150-4799-83ec-bf75c85c1989' => { :name => 'Scooby Snacks' },
                  '8b7d6ab0-a659-11e4-bcd8-0800200c9a66' => { :name => 'Hamburgers' },
                  '134a350a-aead-414f-81a1-29f83cc59b57' => { :name => 'Ascot' },
              }
          },
      }
    end

  end

  mount CoreAPI => '/core'
  mount PayloadAPI => '/payload'

end
