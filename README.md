# Derivora Interval Kit

## About the Project

Derivora Interval Kit is a module designed for working with numerical intervals in Java. This module is part of the Derivora project and provides tools for creating, validating, and transforming intervals in various mathematical contexts.

The project has started active development.

## Objectives of the Module

* Flexibility: Support for various types of intervals (closed, open, mixed).
* Convenience: Simplify working with intervals in applications requiring numerical computations.
* Scalability: Enable future expansion of functionality.

## Project Structure

* Package `xyz.derivora.intervalkit.bounds` provides abstractions for representing and handling interval boundaries.
  * Subpackage `xyz.derivora.intervalkit.bounds.finite` provides implementations for finite interval boundaries.
  * Subpackage `xyz.derivora.intervalkit.bounds.infinite` provides implementations for infinite interval boundaries.
  * Subpackage `xyz.derivora.intervalkit.bounds.comparison` provides utilities for comparing interval boundaries.
  * Subpackage `xyz.derivora.intervalkit.bounds.factory` provides a factory interface for creating boundary instances.

## License

This project is licensed under the GNU Lesser General Public License v3.0.
See the [LICENSE](./LICENSE) file for details.