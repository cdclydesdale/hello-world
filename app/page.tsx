"use client";

import { useMemo, useState } from "react";

const hotels = [
  { name: "The Grand Harbor Hotel", location: "Downtown Waterfront", price: 185.0, pool: true },
  { name: "Maple Street Inn", location: "Historic District", price: 119.0, pool: false },
  { name: "Oceanview Resort", location: "Seaside Boulevard", price: 225.0, pool: true },
  { name: "City Center Suites", location: "Financial District", price: 145.0, pool: false },
  { name: "Sunset Valley Lodge", location: "West Hills", price: 168.0, pool: true },
  { name: "Riverside Budget Hotel", location: "North Riverwalk", price: 89.0, pool: false },
  { name: "Cedar Bay Hotel", location: "Marina Quarter", price: 132.0, pool: true },
  { name: "Willow Creek Suites", location: "Garden District", price: 149.0, pool: true },
];

type SortOrder = "recommended" | "low-to-high" | "high-to-low";

const formatPrice = (price: number) => `$${price.toFixed(2)}`;

export default function Home() {
  const [poolOnly, setPoolOnly] = useState(false);
  const [sortOrder, setSortOrder] = useState<SortOrder>("recommended");

  const visibleHotels = useMemo(() => {
    const filteredHotels = poolOnly ? hotels.filter((hotel) => hotel.pool) : hotels;

    if (sortOrder === "recommended") {
      return filteredHotels;
    }

    return [...filteredHotels].sort((firstHotel, secondHotel) =>
      sortOrder === "low-to-high"
        ? firstHotel.price - secondHotel.price
        : secondHotel.price - firstHotel.price,
    );
  }, [poolOnly, sortOrder]);

  return (
    <main className="page-shell">
      <section className="search-summary" aria-labelledby="page-title">
        <p className="eyebrow">Weekend escape</p>
        <h1 id="page-title">Hotel Search Results</h1>
        <p className="subtitle">
          {visibleHotels.length} stays found for your visit to Harbor City
        </p>
      </section>

      <section className="hotel-filters" aria-label="Filter and sort hotels">
        <label className="pool-filter">
          <input
            type="checkbox"
            checked={poolOnly}
            onChange={(event) => setPoolOnly(event.target.checked)}
          />
          <span>Pool only</span>
        </label>

        <label className="sort-filter">
          <span>Sort by</span>
          <select
            value={sortOrder}
            onChange={(event) => setSortOrder(event.target.value as SortOrder)}
          >
            <option value="recommended">Recommended</option>
            <option value="low-to-high">Price: low to high</option>
            <option value="high-to-low">Price: high to low</option>
          </select>
        </label>
      </section>

      <section className="hotel-results" aria-label="Available hotels" aria-live="polite">
        {visibleHotels.map((hotel) => (
          <article className="hotel-listing" key={hotel.name}>
            <div className="hotel-details">
              <span className="hotel-name">{hotel.name}</span>
              <span className="location">{hotel.location}</span>
              <span className="rating">★ 4.7 guest rating</span>
            </div>
            <div className="hotel-meta">
              {hotel.pool ? <span className="pool-icon">🏊 Pool</span> : null}
              <div className="price-block">
                <span className="night-label">per night</span>
                <span className="price">{formatPrice(hotel.price)}</span>
              </div>
            </div>
          </article>
        ))}
      </section>
    </main>
  );
}
