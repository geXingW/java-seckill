if redis.call('SET', KEYS[1], ARGV[1], 'EX', ARGV[2], 'NX') then return ARGV[1] else return redis.call('GET', 'test-lock') end
