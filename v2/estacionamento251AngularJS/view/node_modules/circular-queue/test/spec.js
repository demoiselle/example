'use strict';

var assert = require('assert');

var CircularQueue = require('../index');

describe('CircularQueue', function () {

  it('starts with size = 0', function () {
    var queue = new CircularQueue(3);
    assert.equal(0, queue.size);
  });

  describe('.offer', function () {
    var queue;

    beforeEach(function () {
      queue = new CircularQueue(3);
    });

    it('increments size', function () {
      queue.offer(14);
      assert.equal(queue.size, 1);
    });

    describe('when size == maxSize', function () {
      beforeEach(function () {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
      });

      it('emits "evict" with evicted item', function () {
        queue.on('evict', function (item) {
          assert.equal(item, 1);
        });
        queue.offer(4);
      });

      it('maintains its size', function () {
        queue.offer(4);
        assert.equal(queue.size, 3);
      });

      it('updates head', function () {
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        assert.equal(queue.peek(), 4);
      });
    });
  });

  describe('.peek', function () {
    var queue;

    beforeEach(function () {
      queue = new CircularQueue(3);
    });

    it('returns first item offered to queue', function () {
      queue.offer(14);
      queue.offer(15);
      assert.equal(queue.peek(), 14);
    });

    describe('when there are no items', function () {
      it('returns undefined', function () {
        assert.equal(queue.peek(), undefined);
      });
    });
  });

  describe('.poll', function () {
    var queue;

    beforeEach(function () {
      queue = new CircularQueue(3);
    });

    it('returns first item offered to queue', function () {
      queue.offer(14);
      queue.offer(15);
      assert.equal(queue.poll(), 14);
    });

    it('removes item from queue', function () {
      queue.offer(14);
      queue.offer(15);
      queue.poll();
      assert.equal(queue.size, 1);
    });

    it('updates head', function () {
      queue.offer(14);
      queue.offer(15);
      queue.poll();
      assert.equal(queue.peek(), 15);
    });

    describe('when there are no items to return', function () {
      it('returns undefined', function () {
        assert.equal(queue.poll(), undefined);
      });
    });
  });
});

