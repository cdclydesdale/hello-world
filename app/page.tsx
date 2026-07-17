const hotels = [
  { name: "The Grand Harbor Hotel", location: "Downtown Waterfront", price: "$185.00", pool: true },
  { name: "Maple Street Inn", location: "Historic District", price: "$119.00", pool: false },
  { name: "Oceanview Resort", location: "Seaside Boulevard", price: "$225.00", pool: true },
  { name: "City Center Suites", location: "Financial District", price: "$145.00", pool: false },
  { name: "Sunset Valley Lodge", location: "West Hills", price: "$168.00", pool: true },
  { name: "Riverside Budget Hotel", location: "North Riverwalk", price: "$89.00", pool: false },
  { name: "Cedar Bay Hotel", location: "Marina Quarter", price: "$132.00", pool: true },
  { name: "Willow Creek Suites", location: "Garden District", price: "$149.00", pool: true },
];

export default function Home() {
  return (
    <main className="page-shell">
      <section className="search-summary" aria-labelledby="page-title">
        <p className="eyebrow">Weekend escape</p>
        <h1 id="page-title">Hotel Search Results</h1>
        <p className="subtitle">8 stays found for your visit to Harbor City</p>
      </section>

      <section className="hotel-results" aria-label="Available hotels">
        {hotels.map((hotel) => (
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
                <span className="price">{hotel.price}</span>
              </div>
            </div>
          </article>
        ))}
      </section>
    </main>
  );
}
