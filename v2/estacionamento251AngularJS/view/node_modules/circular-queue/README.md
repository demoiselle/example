CircularQueue
===============================================================================

A lightweight circular queue, useful for situations where losing stale data is
preferable to unchecked memory growth.

[![Build
Status](https://travis-ci.org/rjz/circular-queue.svg)](https://travis-ci.org/rjz/circular-queue)

Installation
-------------------------------------------------------------------------------

Clone this repository:

    $ npm install circular-queue

Now, instantiate a queue with a fixed maximum size:

    var CircularQueue = require('circular-queue');
    var queue = new CircularQueue(10);

...`offer` it some items:

    queue.offer('one');
    queue.offer('two');
    queue.offer('three');

...and `peek` at or `poll` them from the queue:

    queue.peek(); // 'one'
    queue.poll(); // 'one'
    queue.peek(); // 'two'

### Events

Instances of `CircularQueue` will emit:

  * `'evict'` - when stale items are evicted from the queue

Testing
-------------------------------------------------------------------------------

Lint and run test suite:

    $ npm test

License
-------------------------------------------------------------------------------

MIT

